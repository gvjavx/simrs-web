package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;

import java.sql.Timestamp;

/**
 * Created by reza on 11/06/20.
 */
public class TesTelemedicController implements ModelDriven<Object> {

    private static transient Logger logger = Logger.getLogger(TesTelemedicController.class);

    private String data;
    private String result;
    private TelemedicBo telemedicBoProxy;

    public void setTelemedicBoProxy(TelemedicBo telemedicBoProxy) {
        this.telemedicBoProxy = telemedicBoProxy;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public Object getModel() {
        return null;
    }

    public String index(){
        logger.info(data);
        switch (data){
            case "insert-tele-non-resep":
                insertDataTelemedic("umum", "");
                break;
            case "insert-tele-resep":
                insertDataTelemedic("umum","resep");
                break;
            case "insert-asuransi-non-resep":
                insertDataTelemedic("asuransi", "");
                break;
            default:
                logger.info("==========NO ONE CARE============");
        }
        return result;
    }

    private void insertDataTelemedic(String tipe, String jenis){

        logger.info("[TesTelemedicController.insertDataTelemedic] START >>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());

        ItSimrsAntrianTelemedicEntity antrianTelemedicEntity = new ItSimrsAntrianTelemedicEntity();
        antrianTelemedicEntity.setBranchId("RS01");
        antrianTelemedicEntity.setIdPasien("RS0104200035");
        antrianTelemedicEntity.setIdPelayanan("PYN00000002");
        antrianTelemedicEntity.setIdDokter("DKR00000012");
        antrianTelemedicEntity.setKodeBank("1.1.01.02.01");
        antrianTelemedicEntity.setIdJenisPeriksaPasien(tipe);
        if ("resep".equalsIgnoreCase(jenis)){
            antrianTelemedicEntity.setFlagResep("Y");
        }
        if ("ansuransi".equalsIgnoreCase(tipe)){
            antrianTelemedicEntity.setNoKartu("080780808");
            antrianTelemedicEntity.setIdAsuransi("ASN00000002");
        }
        antrianTelemedicEntity.setCreatedDate(time);
        antrianTelemedicEntity.setCreatedWho("admin");
        antrianTelemedicEntity.setLastUpdate(time);
        antrianTelemedicEntity.setLastUpdateWho("admin");

        try {
            telemedicBoProxy.saveAdd(antrianTelemedicEntity, "RS01", "1.1.01.02.01");
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.insertDataTelemedic] ERROR. ",e);
            throw new GeneralBOException("[TesTelemedicController.insertDataTelemedic] ERROR. ", e);
        }

        logger.info("[TesTelemedicController.insertDataTelemedic] END <<<");
    }


}
