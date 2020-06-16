package com.neurix.hris.mobileapi;

import com.neurix.common.exception.GeneralBOException;
import com.neurix.simrs.transaksi.antriantelemedic.bo.TelemedicBo;
import com.neurix.simrs.transaksi.antriantelemedic.model.ItSimrsAntrianTelemedicEntity;
import com.neurix.simrs.transaksi.reseponline.model.ItSimrsResepOnlineEntity;
import com.neurix.simrs.transaksi.transaksiobat.model.TransaksiObatDetail;
import com.neurix.simrs.transaksi.verifikatorpembayaran.model.ItSimrsPembayaranOnlineEntity;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by reza on 11/06/20.
 */
public class TesTelemedicController implements ModelDriven<Object> {

    private static transient Logger logger = Logger.getLogger(TesTelemedicController.class);

    private String data;
    private String result;
    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Object getModel() {
        return null;
    }

    public String index(){
        logger.info(data);
        switch (data){
            case "insert-umum-non-resep":
                insertDataTelemedic("umum", "");
                break;
            case "insert-umum-resep":
                insertDataTelemedic("umum","resep");
                break;
            case "insert-asuransi-non-resep":
                insertDataTelemedic("asuransi", "");
                break;
            case "insert-resep":
                insertObat(this.id);
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

    private void insertObat(String idTransksiObat){
        logger.info("[TesTelemedicController.insertObat] START >>>");

        Timestamp time = new Timestamp(System.currentTimeMillis());

        TransaksiObatDetail transaksiObatDetail = new TransaksiObatDetail();
        transaksiObatDetail.setIdObat("OBT00000030");
        transaksiObatDetail.setIdPelayanan("PYN00000024");
        transaksiObatDetail.setQty(new BigInteger("5"));
        transaksiObatDetail.setFlag("Y");
        transaksiObatDetail.setAction("C");
        transaksiObatDetail.setCreatedDate(time);
        transaksiObatDetail.setCreatedWho("admin");

        List<TransaksiObatDetail> transaksiObatDetails = new ArrayList<>();
        transaksiObatDetails.add(transaksiObatDetail);
        try {
            telemedicBoProxy.insertResepOnline(idTransksiObat, transaksiObatDetails);
        } catch (GeneralBOException e){
            logger.error("[TesTelemedicController.insertObat] ERROR. ",e);
            throw new GeneralBOException("[TesTelemedicController.insertObat] ERROR. ", e);
        }

        logger.info("[TesTelemedicController.insertObat] END <<<");
    }

}
